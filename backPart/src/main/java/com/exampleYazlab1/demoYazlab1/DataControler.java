package com.exampleYazlab1.demoYazlab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DataYazlab")
@CrossOrigin(origins = "*")
public class DataControler {
    @Autowired
    private DataService dataService;

    long processTime;
    @Autowired
    private DataRepo dataRepo;

    @GetMapping
    public ResponseEntity<Data> getData(){
     return new ResponseEntity<Data>(dataService.findLastText(), HttpStatus.OK);
    }
    @PostMapping
    public String addData(@RequestBody Data data){
        String[] partsOfData = data.getText1().split("-");
        for (int i = 0; i < partsOfData.length; i++) {
            System.out.println(partsOfData[i]);
        }
        StringBuilder partData = new StringBuilder();
        partData.append(partsOfData[0]);
//        for (int i = 0; i < partsOfData.length; i++) {
//            if(i == 0){
//                partData.append(partsOfData[i]);
//            }else{
//                partData.append(" "+partsOfData[i]);
//            }
//
//        }
        long start1 = System.nanoTime();
        CommonWordConcatenation CommonWordConcatenation = new CommonWordConcatenation(data);
        WordConcatenationLight wordConcatenationLight = new WordConcatenationLight();

        if(wordConcatenationLight.concatenateTexts(partsOfData).charAt(0) != Integer.toString(partsOfData.length).charAt(0)){
            data.setResult(wordConcatenationLight.concatenateTexts(partsOfData));

            long end1 = System.nanoTime();
            processTime = end1 - start1;
            data.setTime("Algoritmanın çalışma süresi: " + processTime + " nanosaniye.");
            dataRepo.save(data);
        }else {
            CommonWordConcatenation.extractSentences();
            String result = CommonWordConcatenation.finalControl();
            System.out.println(result);
            System.out.println(partData);
            if(partData.toString().contains(result)){
                data.setResult("Metin Birleştirilemez");
            }else{
                data.setResult(result);
            }

            long end1 = System.nanoTime();
            processTime = end1 - start1;
            data.setTime("Algoritmanın çalışma süresi: " + processTime + " nanosaniye.");
            dataRepo.save(data);
            return result;
        }


        return "";
    }
}
