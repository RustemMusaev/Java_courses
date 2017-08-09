package ru.rustem.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resume")
public class Resume implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "position")
    String position;
    @Column(name = "address")
    String address;
    @Column(name = "study")
    String study;
    @Column(name = "about")
    String about;
    @Column(name = "skill")
    String skill;
    @Column(name = "phone")
    String phone;
    @Column(name = "other")
    String other;


    public Resume() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        if (getId() != null ? !getId().equals(resume.getId()) : resume.getId() != null) return false;
        if (getName() != null ? !getName().equals(resume.getName()) : resume.getName() != null) return false;
        if (getPosition() != null ? !getPosition().equals(resume.getPosition()) : resume.getPosition() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(resume.getAddress()) : resume.getAddress() != null)
            return false;
        if (getStudy() != null ? !getStudy().equals(resume.getStudy()) : resume.getStudy() != null) return false;
        if (getAbout() != null ? !getAbout().equals(resume.getAbout()) : resume.getAbout() != null) return false;
        if (getSkill() != null ? !getSkill().equals(resume.getSkill()) : resume.getSkill() != null) return false;
        if (getPhone() != null ? !getPhone().equals(resume.getPhone()) : resume.getPhone() != null) return false;
        return getOther() != null ? getOther().equals(resume.getOther()) : resume.getOther() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getStudy() != null ? getStudy().hashCode() : 0);
        result = 31 * result + (getAbout() != null ? getAbout().hashCode() : 0);
        result = 31 * result + (getSkill() != null ? getSkill().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getOther() != null ? getOther().hashCode() : 0);
        return result;
    }
}
