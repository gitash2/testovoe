package soft.testovoe.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import soft.testovoe.service.util.DeterministicSelect;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class XlsxProcessorService {

    public Integer findNthMinimum(String path, int n) {
        List<Integer> numbers = readIntegersFromFile(path);

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("The path " + path + " does not contain any numbers");
        }

        if (n <= 0 || n > numbers.size()) {
            throw new IllegalArgumentException("Invalid N value: " + n);
        }

        int[] array = numbers.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        return DeterministicSelect.select(array, n - 1);
    }


    private List<Integer> readIntegersFromFile(String path) {
        List<Integer> list = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    list.add((int) cell.getNumericCellValue());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while reading file:" + e.getMessage(), e);
        }
        return list;
    }
}
