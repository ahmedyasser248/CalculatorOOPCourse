package com.example.demo;

public class CalculatorService{
    public  String Result;
    final String match="-?[0-9]+(.[0-9]*)?\\s+[-X+/]\\s+[0-9]+(.[0-9]*)?";
    public void calculate(String equation) {
        Result="";
        equation=equation.trim();
        double operand1;
        double operand2;
        double result=0;
        boolean error=false;
        String [] arr= equation.split("\\s");
        operand1 = Double.parseDouble(arr[0]);
        operand2 = Double.parseDouble(arr[2]);

        if(equation.contains("+")){
            result=operand1+operand2;

        }
        else if(equation.contains("X")){
            result=operand1*operand2;
        }
        else if(equation.contains("/")){
            if(operand2==0){
                error =true;
            }else{
                result=operand1/operand2;}
        }
        else{
            result=operand1-operand2;
        }

        if(error){
            Result="E";
        }
        else{
            Result=result+"";
        }


    }

    public String evaluatePower(String equation){
        Result="";
        equation=equation.trim();
        if(equation.matches(match)){
            calculate(equation);
            if(Result=="E"){
                return Result ;
            }
            double result=Double.parseDouble(Result);
            result=result*result;
            Result=result+"";
            return Result;

        }
        char end=equation.charAt(equation.length()-1);
        if(Character.isDigit(end)||end=='.'){
            double result=Double.parseDouble(equation);
            result=Math.pow(result, 2);
            Result=result+"";
            return Result;
        }
        String num=equation.substring(0,equation.length()-1);
        double result=Double.parseDouble(num);
        if(equation.contains("+")){
            result=result+Math.pow(result, 2);
        }else if(equation.contains("/")){
            result=result/Math.pow(result, 2);
        }else if(equation.contains("X")){
            result=Math.pow(result,3);
        }else{
            result=result-Math.pow(result, 2);
        }
        Result=result+"";
        return Result;


    }

    public String evaluateRoot(String equation){
        Result="";
        equation=equation.trim();
        if(equation.matches(match)){
            calculate(equation);
            if(Result=="E"){
                return Result ;
            }

            double result=Double.parseDouble(Result);
            if(result<0){
                Result="E";
                return Result;
            }
            result=Math.sqrt(result);
            Result=result+"";
            return Result;
        } char end=equation.charAt(equation.length()-1);
        if(Character.isDigit(end)||end=='.'){
            double result=Double.parseDouble(equation);
            if(result<0){
                Result="E";
                return Result;
            }
            result=Math.sqrt(result);
            Result=result+"";
            return Result;
        }
        else{
            String sub=equation.substring(0, equation.length()-1);
            double calc=Double.parseDouble(sub);
            if(calc<0){
                Result="E";
                return Result;
            }

        }
        String num=equation.substring(0,equation.length()-1);
        double result=Double.parseDouble(num);
        if(result<0){
            Result="E";
            return Result;
        }

        if(equation.contains("+")){
            result=result+Math.sqrt(result);
        }else if(equation.contains("/")){
            result=result/Math.sqrt(result);
        }else if(equation.contains("X")){
            result= result*Math.sqrt(result);
        }else{
            result=result-Math.sqrt(result);
        }
        Result=result+"";
        return Result;
    }

    public String inverse(String equation){
        Result="";
        equation=equation.trim();
        if(equation.matches(match)){
            calculate(equation);
            if(Result=="E"){
                return Result;
            }
            double result=Double.parseDouble(Result);
            if(result==0){
                Result="E";
                return Result;
            }
            result=Math.pow(result, -1);
            Result=result+"";
            return Result ;
        }
        if(Character.isDigit( equation.charAt(equation.length()-1))||equation.charAt(equation.length()-1)=='.'){
            double res=Double.parseDouble(equation);
            if(res==0){
                Result="E";
                return Result;
            }
            res=Math.pow(res, -1);
            Result=res+"";
            return Result ;
        }
        String number= equation.substring(0, equation.length()-1);
        double result=Double.parseDouble(number);
        if(equation.contains("+")){
            result=result+Math.pow(result, -1);
        }else if(equation.contains("/")){
            if(result==0){
                Result="E";
                return Result;
            }
            result=result/Math.pow(result, -1);

        }else if(equation.contains("X")){
            result= result*Math.pow(result,-1);
        }else{
            result=result-Math.pow(result,-1);
        }

        Result=result+"";
        return Result;

    }
    public String percentage(String equation){
        Result="";
        equation=equation.trim();
        if(!equation.matches(match)){
            Result="0";
            return Result;
        }else{
            double operand1;
            double operand2;
            double result=0;
            String [] arr= equation.split("\\s");
            operand1 = Double.parseDouble(arr[0]);
            operand2 = Double.parseDouble(arr[2]);
            operand2=(operand2*operand1)/100;
            if(equation.contains("+")){
                result=operand1+operand2;
            }
            else if(equation.contains("/")){
                result=operand1/operand2;
            }
            else if(equation.contains("X")){
                result=operand1*operand2;
            }else{
                result=operand1-operand2;
            }
            Result=result+"";
            return Result;
        }

    }

}
