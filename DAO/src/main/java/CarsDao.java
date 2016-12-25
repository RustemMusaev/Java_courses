import java.util.List;

public interface CarsDao {
    Cars find(int id);
    List<Cars> findAll();
    boolean save(Cars cars);
    boolean update(Cars cars);
    boolean delete(int id);
}