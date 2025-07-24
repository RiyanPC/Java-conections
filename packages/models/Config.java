package packages.models;

public class Config {
    private int id;
    private String user;
    private String password;
    private String creationTime;

public Config(){

}


public Config(int id, String user, String password) {
    this.id = id;
    this.user = user;
    this.password = password;
    this.creationTime = creationTime;
}

public int getId() {
        return id;
}

public void setId(int id) {
        this.id = id;
}

public String getUser() {
        return user;
}

public void setUser(String user) {
        this.user = user;
}

public String getPassword() {
        return password;
}

public void setPassword(String password) {
        this.password = password;
}

public String getCreationTime() {
        return creationTime;
}

public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
}

}
