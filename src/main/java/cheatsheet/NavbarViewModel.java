package cheatsheet;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NavbarViewModel {
    String language;
    List<String> subjects;
}
