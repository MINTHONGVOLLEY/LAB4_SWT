/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package molder;

/**
 *
 * @author quanb
 */
public class CategoryBlog {
  private int categoryblogid;
  private String categoryblogname;
  private int categoryblogstatus;
  private String slider;

    public CategoryBlog() {
    }

    public CategoryBlog(int categoryblogid, String categoryblogname, int categoryblogstatus, String slider) {
        this.categoryblogid = categoryblogid;
        this.categoryblogname = categoryblogname;
        this.categoryblogstatus = categoryblogstatus;
        this.slider = slider;
    }

    public int getCategoryblogid() {
        return categoryblogid;
    }

    public void setCategoryblogid(int categoryblogid) {
        this.categoryblogid = categoryblogid;
    }

    public String getCategoryblogname() {
        return categoryblogname;
    }

    public void setCategoryblogname(String categoryblogname) {
        this.categoryblogname = categoryblogname;
    }

    public int getCategoryblogstatus() {
        return categoryblogstatus;
    }

    public void setCategoryblogstatus(int categoryblogstatus) {
        this.categoryblogstatus = categoryblogstatus;
    }

    public String getSlider() {
        return slider;
    }

    public void setSlider(String slider) {
        this.slider = slider;
    }

    @Override
    public String toString() {
        return "CategoryBlog{" + "categoryblogid=" + categoryblogid + ", categoryblogname=" + categoryblogname + ", categoryblogstatus=" + categoryblogstatus + ", slider=" + slider + '}';
    }
  
}
