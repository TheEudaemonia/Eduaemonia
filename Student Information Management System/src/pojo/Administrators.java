package pojo;

public class Administrators {
    private String AdminName;
    private String AdminPassword;

    @Override
    public String toString() {
        return "管理员名字:"+ AdminName +","+"管理员密码:"+ AdminPassword + ";";
    }

    public Administrators() {
    }

    public Administrators(String adminName, String adminPassword) {
        AdminName = adminName;
        AdminPassword = adminPassword;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getAdminPassword() {
        return AdminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        AdminPassword = adminPassword;
    }
}
