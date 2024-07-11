/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package molder;

/**
 *
 * @author quanb
 */
public class Blog {
    private int blogid;
    private String blogtitle;
    private String blogdate;
    private String blogcontent;
    private String blogthumnail;
    private int adminid;
    private int categoryblogid;
    private int blogstatus;
    private String img;
    private String url;

    public Blog() {
    }

    public Blog(int blogid, String blogtitle, String blogdate, String blogcontent, String blogthumnail, int adminid, int categoryblogid, int blogstatus, String img, String url) {
        this.blogid = blogid;
        this.blogtitle = blogtitle;
        this.blogdate = blogdate;
        this.blogcontent = blogcontent;
        this.blogthumnail = blogthumnail;
        this.adminid = adminid;
        this.categoryblogid = categoryblogid;
        this.blogstatus = blogstatus;
        this.img = img;
        this.url = url;
    }

    public int getBlogid() {
        return blogid;
    }

    public void setBlogid(int blogid) {
        this.blogid = blogid;
    }

    public String getBlogtitle() {
        return blogtitle;
    }

    public void setBlogtitle(String blogtitle) {
        this.blogtitle = blogtitle;
    }

    public String getBlogdate() {
        return blogdate;
    }

    public void setBlogdate(String blogdate) {
        this.blogdate = blogdate;
    }

    public String getBlogcontent() {
        return blogcontent;
    }

    public void setBlogcontent(String blogcontent) {
        this.blogcontent = blogcontent;
    }

    public String getBlogthumnail() {
        return blogthumnail;
    }

    public void setBlogthumnail(String blogthumnail) {
        this.blogthumnail = blogthumnail;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public int getCategoryblogid() {
        return categoryblogid;
    }

    public void setCategoryblogid(int categoryblogid) {
        this.categoryblogid = categoryblogid;
    }

    public int getBlogstatus() {
        return blogstatus;
    }

    public void setBlogstatus(int blogstatus) {
        this.blogstatus = blogstatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogid=" + blogid + ", blogtitle=" + blogtitle + ", blogdate=" + blogdate + ", blogcontent=" + blogcontent + ", blogthumnail=" + blogthumnail + ", adminid=" + adminid + ", categoryblogid=" + categoryblogid + ", blogstatus=" + blogstatus + ", img=" + img + ", url=" + url + '}';
    }
    
    
}
