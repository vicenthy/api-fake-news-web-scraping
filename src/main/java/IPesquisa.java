import java.util.List;

public interface IPesquisa {
    List<ArtigoDTO> verificarFakeNews(String parametro);
}
