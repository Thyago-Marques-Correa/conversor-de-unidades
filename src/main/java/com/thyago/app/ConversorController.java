package com.thyago.app;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConversorController {

    private static final Map<String, String> TIPOS = new LinkedHashMap<>();
    static {
        TIPOS.put("c-f", "Celsius para Fahrenheit");
        TIPOS.put("f-c", "Fahrenheit para Celsius");
        TIPOS.put("km-mi", "Quilometros para milhas");
        TIPOS.put("mi-km", "Milhas para quilometros");
        TIPOS.put("kg-lb", "Quilos para libras");
        TIPOS.put("lb-kg", "Libras para quilos");
        TIPOS.put("m-ft", "Metros para pes");
        TIPOS.put("ft-m", "Pes para metros");
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tipos", TIPOS);
        return "index";
    }

    @PostMapping("/converter")
    public String converter(@RequestParam double valor, @RequestParam String tipo, Model model) {
        double r = switch (tipo) {
            case "c-f" -> valor * 9 / 5 + 32;
            case "f-c" -> (valor - 32) * 5 / 9;
            case "km-mi" -> valor * 0.621371;
            case "mi-km" -> valor / 0.621371;
            case "kg-lb" -> valor * 2.20462;
            case "lb-kg" -> valor / 2.20462;
            case "m-ft" -> valor * 3.28084;
            case "ft-m" -> valor / 3.28084;
            default -> valor;
        };
        model.addAttribute("tipos", TIPOS);
        model.addAttribute("valor", valor);
        model.addAttribute("tipo", tipo);
        model.addAttribute("resultado", String.format("%.2f", r));
        return "index";
    }
}
