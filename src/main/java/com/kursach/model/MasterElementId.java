package com.kursach.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class MasterElementId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "master_id")
    private DAOMaster daoMaster;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "elem_id")
    private DAOElement daoElement;

}
