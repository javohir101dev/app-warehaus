package com.appwarehaus.entity;

import com.appwarehaus.entity.template.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Measurement extends AbstractEntity {

}
