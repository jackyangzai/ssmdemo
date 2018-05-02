package com.wonder.entity;

public class Drug {
    private Integer id;
    private String drug_no;
    private String drug_name;
    private String drug_com_name;
    private String drug_category;
    private String drug_spec;
    private String drug_pro_address;
    private String drug_status;
    private Integer drug_qty;

    public Drug() {
    }

    public Drug(Integer id, String drug_no, String drug_name, String drug_com_name, String drug_category, String drug_spec, String drug_pro_address, String drug_status, Integer drug_qty) {
        this.id = id;
        this.drug_no = drug_no;
        this.drug_name = drug_name;
        this.drug_com_name = drug_com_name;
        this.drug_category = drug_category;
        this.drug_spec = drug_spec;
        this.drug_pro_address = drug_pro_address;
        this.drug_status = drug_status;
        this.drug_qty = drug_qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrug_no() {
        return drug_no;
    }

    public void setDrug_no(String drug_no) {
        this.drug_no = drug_no;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDrug_com_name() {
        return drug_com_name;
    }

    public void setDrug_com_name(String drug_com_name) {
        this.drug_com_name = drug_com_name;
    }

    public String getDrug_category() {
        return drug_category;
    }

    public void setDrug_category(String drug_category) {
        this.drug_category = drug_category;
    }

    public String getDrug_spec() {
        return drug_spec;
    }

    public void setDrug_spec(String drug_spec) {
        this.drug_spec = drug_spec;
    }

    public String getDrug_pro_address() {
        return drug_pro_address;
    }

    public void setDrug_pro_address(String drug_pro_address) {
        this.drug_pro_address = drug_pro_address;
    }

    public String getDrug_status() {
        return drug_status;
    }

    public void setDrug_status(String drug_status) {
        this.drug_status = drug_status;
    }

    public Integer getDrug_qty() {
        return drug_qty;
    }

    public void setDrug_qty(Integer drug_qty) {
        this.drug_qty = drug_qty;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", drug_no='" + drug_no + '\'' +
                ", drug_name='" + drug_name + '\'' +
                ", drug_com_name='" + drug_com_name + '\'' +
                ", drug_category='" + drug_category + '\'' +
                ", drug_spec='" + drug_spec + '\'' +
                ", drug_pro_address='" + drug_pro_address + '\'' +
                ", drug_status='" + drug_status + '\'' +
                ", drug_qty=" + drug_qty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drug drug = (Drug) o;

        if (id != null ? !id.equals(drug.id) : drug.id != null) return false;
        if (drug_no != null ? !drug_no.equals(drug.drug_no) : drug.drug_no != null) return false;
        if (drug_name != null ? !drug_name.equals(drug.drug_name) : drug.drug_name != null) return false;
        if (drug_com_name != null ? !drug_com_name.equals(drug.drug_com_name) : drug.drug_com_name != null)
            return false;
        if (drug_category != null ? !drug_category.equals(drug.drug_category) : drug.drug_category != null)
            return false;
        if (drug_spec != null ? !drug_spec.equals(drug.drug_spec) : drug.drug_spec != null) return false;
        if (drug_pro_address != null ? !drug_pro_address.equals(drug.drug_pro_address) : drug.drug_pro_address != null)
            return false;
        if (drug_status != null ? !drug_status.equals(drug.drug_status) : drug.drug_status != null) return false;
        return drug_qty != null ? drug_qty.equals(drug.drug_qty) : drug.drug_qty == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (drug_no != null ? drug_no.hashCode() : 0);
        result = 31 * result + (drug_name != null ? drug_name.hashCode() : 0);
        result = 31 * result + (drug_com_name != null ? drug_com_name.hashCode() : 0);
        result = 31 * result + (drug_category != null ? drug_category.hashCode() : 0);
        result = 31 * result + (drug_spec != null ? drug_spec.hashCode() : 0);
        result = 31 * result + (drug_pro_address != null ? drug_pro_address.hashCode() : 0);
        result = 31 * result + (drug_status != null ? drug_status.hashCode() : 0);
        result = 31 * result + (drug_qty != null ? drug_qty.hashCode() : 0);
        return result;
    }
}
