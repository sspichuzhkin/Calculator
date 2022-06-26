package pro.sky.java.course2.calculator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.calculator.service.CalculatorService;

import java.util.Objects;

@RestController
@RequestMapping("/calculator")
public class Controller {

    private final CalculatorService calculatorService;

    public Controller(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String webSiteAnswer() {
        return "<h1>Welcome to the calculator</h1>";
    }

    @GetMapping("/plus")
    public String plus(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return "Missing one or more parameters";
        }
        return buildResult(a, b, calculatorService.plus(a, b), "+");
    }

    @GetMapping("/minus")
    public String minus(@RequestParam(value = "num1", required = false) Integer a,
                        @RequestParam(value = "num2", required = false) Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return "Missing one or more parameters";
        }
        return buildResult(a, b, calculatorService.minus(a, b), "-");
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam(value = "num1", required = false) Integer a,
                           @RequestParam(value = "num2", required = false) Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return "Missing one or more parameters";
        }
        return buildResult(a, b, calculatorService.multiply(a, b), "*");
    }

    @GetMapping("/divide")
    public String divide(@RequestParam(value = "num1", required = false) Integer a,
                         @RequestParam(value = "num2", required = false) Integer b) {
        if (Objects.isNull(a) || Objects.isNull(b)) {
            return "Missing one or more parameters";
        }
        if (b == 0) {
            return "Cant divide by 0";
        }
        return buildResult(a, b, calculatorService.divide(a, b), "/");
    }

    private String buildResult(Integer a, Integer b, Number result, String operationType) {
        return String.format("%s %s %s = %s", a, operationType, b, result);
    }
}
