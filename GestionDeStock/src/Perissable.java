import java.time.LocalDate;

public interface Perissable {

    public boolean isExpired(LocalDate toDay);
}
