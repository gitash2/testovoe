package soft.testovoe.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soft.testovoe.service.XlsxProcessorService;

@RestController
@RequestMapping("/api/v1/xlsx")
@RequiredArgsConstructor
public class ExcelController {

    private final XlsxProcessorService xlsxProcessorService;

    @Operation(summary = "Get nth minimum number from XLSX file")
    @GetMapping("/nth-min")
    public Integer getNthMinimum(@RequestParam String path, @RequestParam Integer n ) {
        return xlsxProcessorService.findNthMinimum(path, n);
    }
}
