package com.atguigu.java.constant;



/**
 * @author dwb
 * @create 2020-11-29 20:53
 */
public enum CountryEnum
{
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");


     private Integer rtCode;
     private String rtMessage;

    CountryEnum(Integer rtCode, String rtMessage) {
        this.rtCode = rtCode;
        this.rtMessage = rtMessage;
    }

    public Integer getRtCode() {
        return rtCode;
    }

    public String getRtMessage() {
        return rtMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){

        CountryEnum[] countryEnums = CountryEnum.values();

        for (CountryEnum element : countryEnums) {

            if(index == element.getRtCode()){
                return element;
            }
        }
        return null;
    }
}
