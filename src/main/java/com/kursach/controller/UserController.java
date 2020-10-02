package com.kursach.controller;

import com.kursach.dao.*;
import com.kursach.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

	@Autowired
	RequestDao requestDao;

	@Autowired
	MasterEntityDao masterEntityDao;

	@Autowired
	AttributesDao attributesDao;

	@Autowired
	ElemAttributesDao elemAttributesDao;

	@Autowired
	ElemDao elemDao;

	@Autowired
	ElemTypeDao elemTypeDao;

	@Autowired
	DeptDao deptDao;

	@Autowired
	RoleDao roleDao;

	@GetMapping("/attrdel")
	public String delAttr(@RequestParam(value = "id") Integer id ) {
		attributesDao.deleteById(id);
		return "redirect:addadmin";
	}

	@PostMapping("/elemattrvalues")
	public String addElem(@ModelAttribute DAOAttributes daoAttributes) {
		attributesDao.save(daoAttributes);
		return "redirect:addadmin";
	}


	@GetMapping("/elemattrdel")
	public String delElemAttr(@RequestParam(value = "id") Integer id ) {
		elemAttributesDao.deleteById(id);
		return "redirect:addadmin";
	}

	@PostMapping("/elemattr")
	public String addElem(@ModelAttribute DAOElemAttributes daoElemAttributes) {
		elemAttributesDao.save(daoElemAttributes);
		return "redirect:addadmin";
	}

	@GetMapping("/elemdel")
	public String delElem(@RequestParam(value = "id") Integer id ) {
		elemDao.deleteById(id);
		return "redirect:addadmin";
	}

	@PostMapping("/elem")
	public String addElem(@ModelAttribute DAOElement daoElement) {
		elemDao.save(daoElement);
		return "redirect:addadmin";
	}

	@GetMapping("/elemtypedel")
	public String delElemType(@RequestParam(value = "id") Integer id ) {
		elemTypeDao.deleteById(id);
		return "redirect:addadmin";
	}

	@PostMapping("/elemtype")
	public String addElemType(@ModelAttribute DAOElementType daoElementType) {
		elemTypeDao.save(daoElementType);
		return "redirect:addadmin";
	}

	@GetMapping("/addadmin")
	public String addAdmin(Model model, @PageableDefault(size = 10) @Qualifier("first") Pageable first, @PageableDefault(size = 10) @Qualifier("second") Pageable second, @PageableDefault(size = 10) @Qualifier("third") Pageable third, @PageableDefault(size = 10) @Qualifier("fourth") Pageable fourth) {
	 model.addAttribute("elemtype",elemTypeDao.findAll(first));
	 model.addAttribute("elements",elemDao.findAll(second));
	 model.addAttribute("elements_attribute",elemAttributesDao.findAll(third));
	 model.addAttribute("attr_values",attributesDao.findAll(fourth));
	 return "add";
}
	@GetMapping("/closereq")
	public String close(@RequestParam(value = "id") Integer id) {
		DAORequest daoRequest = requestDao.getOne(id);
		daoRequest.setReady(true);
		requestDao.save(daoRequest);
		return "redirect:closed";
	}


	@GetMapping("/closed")
	public String closeRequest(Model model, Pageable pageable){
		model.addAttribute("closed",requestDao.findAllByReady(false, pageable));
		return "closed";
	}

	@PostMapping("/filter")
	public String filtered(@ModelAttribute(value = "filter") DAORequest daoRequest, Model model) {
		DAORequest filter = new DAORequest();
		filter.setBuild(daoRequest.getBuild());
		filter.setId(daoRequest.getId());
		Specification<DAORequest> spec = new ReqSpecification(filter);
		model.addAttribute("filtered", requestDao.findAll(spec));
		return "filtered";
	}

	@PostMapping("/addsklad")
    public String putSklad(@ModelAttribute("tosklad") AttrDTO attrDTO) {
		DAOAttributes daoAttributeToUpdate = attributesDao.getOne(attrDTO.getId());
		daoAttributeToUpdate.setAmount(attrDTO.getAmount());
		attributesDao.save(daoAttributeToUpdate);
		return "redirect:sklad";
    }

	@GetMapping("/sklad")
    public String getSklad(Model model,  @PageableDefault(size = 10) @Qualifier("first") Pageable first,  @PageableDefault(size = 10) @Qualifier("second") Pageable second,  @PageableDefault(size = 10) @Qualifier("third") Pageable third) {
	   model.addAttribute("sklad",attributesDao.findAllAttrs(first));
	   model.addAttribute("readies", masterEntityDao.findGroupByAttr(second));
	   return "sklad";
    }


	@GetMapping("/reqelem")
	public String reqElems( @SortDefault("amount") @PageableDefault(size = 5) Pageable pageable, @RequestParam(value = "id") Integer id, Model model){
		model.addAttribute("allElemAttrs",elemAttributesDao.findAll());
		model.addAttribute("allElems",elemDao.findAll());
		model.addAttribute("reqelems", masterEntityDao.findAllByreqId(id, pageable));
		model.addAttribute("numberofreq", id);
		return "secureelems";
	}

	@GetMapping("/")
	public String firstPage() {
		return "login";
	}

	@GetMapping("/login")
	public String logPage() {
		return "login.html";
	}

	@RequestMapping("/success")
	public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response,  Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if(roles.contains("ROLE_ADMIN")){
			response.sendRedirect("/user");
		}

		if(roles.contains("ROLE_MASTER")){
			response.sendRedirect("/user");
		}
		else if(roles.contains("ROLE_SKLAD")) {
			response.sendRedirect("/sklad");
		}
	}

	@GetMapping("/reg")
	public String regPage(Model model) {
		model.addAttribute("checker", roleDao.findAll());
		return "register";
	}

	@GetMapping("/user")
	public String userPage(Model model, @PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("depts", deptDao.findAll());
		model.addAttribute("elemstype", elemTypeDao.findAll());
		model.addAttribute("elems", elemDao.findAll());
		model.addAttribute("attr", elemAttributesDao.findAll());
		model.addAttribute("attr_values", attributesDao.findAll());
		model.addAttribute("last_id", requestDao.findFirstByOrderByIdDesc());
		model.addAttribute("request",requestDao.findAll(pageable));
		return "secure";
	}

	@GetMapping("/logout")
	public String logout() {
		return "login.html";
	}

	@PostMapping("/add")
	public String sendMaster(@ModelAttribute("command") MasterDTO masterDTO){
		if(masterDTO.getId() != null) {
		masterDTO.setArrayId(removeNull(masterDTO.getArrayId()));
		masterDTO.setArrayValue(removeNull(masterDTO.getArrayValue()));
			List<DAOMaster> daoMasterEntities = new ArrayList<>(
					List.of(new DAOMaster(masterDTO.getArrayId().get(0),masterDTO.getArrayValue().get(0),
							masterDTO.getReq_id() + 1)));

			for (int i = 1; i < masterDTO.getArrayId().size(); i++) {
				DAOMaster daoMasterEntity = new DAOMaster(masterDTO.getArrayId().get(i),
						masterDTO.getArrayValue().get(i),masterDTO.getReq_id() + 1);
				daoMasterEntities.add(daoMasterEntity);
			}
			List<DAORequest> daoRequestList = new ArrayList<>(
					List.of(new DAORequest(masterDTO.getBuildName(),masterDTO.getId())));
			requestDao.saveAll(daoRequestList);
			masterEntityDao.saveAll(daoMasterEntities);
			daoRequestList.clear();
			daoMasterEntities.clear();
		}
		return "redirect:user";
	}

	public <T> ArrayList<T> removeNull(ArrayList<T> a) {
		ArrayList<T> removedNull = new ArrayList<T>();
		for (T str : a)
			if (str != null)
				removedNull.add(str);
		return removedNull;
	}

}

