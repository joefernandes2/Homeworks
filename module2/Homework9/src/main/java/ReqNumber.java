import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


@WebServlet("/")
public class ReqNumber extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static int counter = 0;
    private File file = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setContentType("image/jpeg");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        counter++;
        BufferedImage image = new BufferedImage(500, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setFont(new Font("Serif", Font.ITALIC, 48));
        graphics.setColor(Color.green);
        graphics.drawString(counter + "", 100, 100);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpeg", out);
        final PrintWriter writer = resp.getWriter();
        writer.println("<h1>" + counter + "</h1>");
        countWriter();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String path = config.getServletContext().getRealPath("//WEB-INF/counter.txt");
        file = new File(path);
        try (FileReader reader = new FileReader(file); Scanner scan = new Scanner(reader)) {
            while (scan.hasNextLine()) {
                counter = scan.nextInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void countWriter() throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(counter + "");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
