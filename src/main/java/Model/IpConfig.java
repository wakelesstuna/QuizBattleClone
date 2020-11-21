package Model;

public enum IpConfig {
    IPADDRESS("127.0.0.1"),
    PORT("45455");

    private final String s;

    IpConfig(String s){
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
