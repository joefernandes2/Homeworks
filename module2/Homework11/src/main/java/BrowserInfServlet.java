import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;


public class BrowserInfServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static LinkedHashMap<String, String> browserNames = new LinkedHashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        browserNames.put("OPR", "Opera");
        browserNames.put("Edg", "Microsoft Edge");
        browserNames.put("Trident", "Internet Explorer");
        browserNames.put("YaBrowser", "Yandex Browser");
        browserNames.put("CriOS", "Google Chrome");
        browserNames.put("FxiOS", "Mozilla Firefox");
        browserNames.put("Brave", "Brave");
        browserNames.put("Vivaldi", "Vivaldi");
        browserNames.put("SeaMonkey", "SeaMonkey");
        browserNames.put("Chrome", "Google Chrome");
        browserNames.put("Firefox", "Mozilla Firefox");
        browserNames.put("Safari", "Safari");
    }
    private String hello(String info) {
        String browserName = "Unknown browser";
        for (String pattern : browserNames.keySet()) {
            if (info != null && info.contains(pattern)) {
                browserName = browserNames.get(pattern);
                break;
            }
        }
        return "Hello user " + browserName;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter writer = resp.getWriter();
        writer.println(hello(req.getHeader("user-agent")));
    }


}
