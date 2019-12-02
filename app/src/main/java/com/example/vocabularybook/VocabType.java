package com.example.vocabularybook;

public class VocabType {

    String Chinese;
    String English;
    String Myanmar;
    String id;

    public VocabType(String Chinese,String English,String Myanmar,String id){
        this.Chinese = Chinese;
        this.English = English;
        this.Myanmar = Myanmar;
        this.id = id;
    }

    public String getChinese(){
        return Chinese;
    }

    public void setChinese(String Chinese){
        this.Chinese = Chinese;
    }

    public String getEnglish(){
        return English;
    }

    public void setEnglish(String English){
        this.English = English;
    }

    public String getMyanmar(){
        return Myanmar;
    }

    public void setMyanmar(String Myanmar){
        this.Myanmar = Myanmar;
    }

    public String getId(){return id;}

    public void setId(String id){this.id = id;}
}
