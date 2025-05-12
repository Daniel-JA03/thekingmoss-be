package com.thekingmoss.props;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("app.security")
public record SecurityProperties(
        Csrf csrf,
        UserConfig user
) {
    public record Csrf(boolean enabled){}
    public record UserConfig(Memory memory){}
    public record Memory(List<Users> users){}
    public record Users(String name, String password, List<String> roles){}
}

