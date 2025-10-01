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
    protected List<HashMap<String, Object>> data;

    public CsvImporter(String filePath) {
        this.filePath = filePath;
        this.data = new ArrayList<>();

        this.processCsvData();
    }

    public String[] getColumnHeaders() {
        return this.columnHeaders;
    }

    public void processCsvData() {
        List<HashMap<String, Object>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                return;
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
        this.data = data;
    }
}
