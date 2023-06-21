package com.exampleYazlab1.demoYazlab1;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("yazlab")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    @Id
    private ObjectId id;
    private String text1;
    private String result;

    private String time;

}
