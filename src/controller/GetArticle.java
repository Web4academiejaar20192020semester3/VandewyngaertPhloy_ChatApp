package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetArticle extends AsyncRequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> articles = this.getArticles(6);
        String articlenrStr = request.getParameter("nr");// nr van artikel opvragen
        int articlenr = Integer.parseInt(articlenrStr);


        if (!(articles.get(articlenr - 1) == null)) {// zolang articlenr niet null is gaan we naar de juiste artiekel
            request.getRequestDispatcher("a" + articlenr + ".jsp").forward(request, response);// a+nr die je krijt+jsp
        }
        return "blog.jsp";//anders naar blog gaan
    }

    private List<String> getArticles(int length) {
        List<String> articles = new ArrayList<>();

        for (int i = 0; i < length; i++) {//lijst om 5 cijfers te krijgen, dit stelt elk een artikel nr voor

            String s = "" + i;
            articles.add(s);
        }

        return articles;
    }
}
