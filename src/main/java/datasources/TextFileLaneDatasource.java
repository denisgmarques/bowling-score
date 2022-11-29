package datasources;

import com.bowling.exception.DataFileNotFoundException;
import com.bowling.model.Lane;
import com.bowling.utils.RetrieveDataFromFile;

import java.io.FileNotFoundException;

public class TextFileLaneDatasource implements LaneDatasource {
    private String path;

    public TextFileLaneDatasource(String path) {
        this.path = path;
    }

    @Override
    public Lane getLane() {
        try {
            return RetrieveDataFromFile.loadFile(path);
        } catch (FileNotFoundException e) {
            throw new DataFileNotFoundException(e.getMessage());
        }
    }
}
