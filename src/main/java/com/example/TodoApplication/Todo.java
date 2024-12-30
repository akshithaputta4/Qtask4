package com.example.TodoApplication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "todos")
public class Todo {
    @Id
    private String id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("completed")
    private boolean completed;



}
