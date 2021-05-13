package com.example.demo;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class Controller {
    CalculatorService calculator = new CalculatorService();
    @RequestMapping("/get")
    public String getData(){
        System.out.println(calculator.Result);
        return calculator.Result;
    }

    @PostMapping(value="/evaluate")
    public void requestMethodName(@RequestBody String param) {
        calculator.calculate(param);
    }

    @RequestMapping(value="/power",method = RequestMethod.POST)
    public void evaluatepower(@RequestBody String param){

        calculator.evaluatePower(param);
    }
    @RequestMapping(value="/root", method = RequestMethod.POST)
    public void evaluateRoot(@RequestBody String param){
        calculator.evaluateRoot(param);

    }
    @RequestMapping(value = "/inverse",method=RequestMethod.POST)
    public void inverse(@RequestBody String param){
        calculator.inverse(param);
    }
    @RequestMapping(value = "/percentage", method = RequestMethod.POST)
    public void percentage(@RequestBody String param){
        calculator.percentage(param);
    }

}
