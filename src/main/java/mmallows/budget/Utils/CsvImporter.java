package mmallows.budget.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CsvImporter {

    private String filePath;
    private String[] columnHeaders;

    public CsvImporter(String filePath) {
        this.filePath = filePath;
    }

    public String[] getColumnHeaders() {
        return columnHeaders;
    }

    public List<HashMap<String, Object>> processCsvData() {
        List<HashMap<String, Object>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                return data;
            }
            this.columnHeaders = headerLine.split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length != this.columnHeaders.length) {
                    System.err.println("Skipping line due to column mismatch: " + line);
                    continue;
                }

                HashMap<String, Object> row = new HashMap<>();
                for (int rowNum = 0; rowNum < this.columnHeaders.length && rowNum < values.length; rowNum++) {
                    row.put(this.columnHeaders[rowNum].trim(), values[rowNum].trim());
                }
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
