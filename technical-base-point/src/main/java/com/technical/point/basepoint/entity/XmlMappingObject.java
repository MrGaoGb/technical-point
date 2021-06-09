package com.technical.point.basepoint.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Description Xml映射对象
 *   @XmlRootElement: 作用于类，表示该类与xml的主节点映射
 * @Author gaogba
 * @Date 2021/1/19 10:10
 * @Version 1.0
 */
@Getter
@Setter
@XmlRootElement(name = "channel")
public class XmlMappingObject {

    @XmlElement(name = "name",required = true)
    private String userName;


}
