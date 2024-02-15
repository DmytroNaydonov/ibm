package model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data

public class Joke {

    private final String type;
    private final String setup;
    private final String punchline;
    private final int id;
}
