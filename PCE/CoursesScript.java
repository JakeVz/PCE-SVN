import java.io.*;
import java.net.*;
import java.util.regex.*;

public class CoursesScript
{
    public static void main(String[] args) throws Exception
    {
        URL site = new URL("http://catalog.calpoly.edu/coursesaz/");
        BufferedReader in = new BufferedReader(
            new InputStreamReader(site.openStream()));
        PrintStream out = new PrintStream(new FileOutputStream("courses.txt"));
        System.setOut(out);
        getDepartments(in);
        in.close();
    }

    public static void getDepartments(BufferedReader in) throws Exception
    {
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            if (inputLine.compareTo("<table>") == 0)
            {
                break;
            }
        }

        while ((inputLine = in.readLine()).compareTo("</table>") != 0)
        {
            String department = inputLine.substring(
                inputLine.indexOf("(") + 1, inputLine.indexOf(")"));
            System.out.println("*** " + department + " ***");
            getCourses(getDepartmentURL(inputLine));
            System.out.println("\n");
        }
    }

    private static String getDepartmentURL(String inputLine)
    {
        String link = "";
        Matcher match = Pattern
            .compile("href=\"([/a-z]+)\"")
            .matcher(inputLine);

        if (match.find())
        {
            link = match.group(1);
        }

        return "http://catalog.calpoly.edu" + link;
    }

    private static void getCourses(String link) throws Exception
    {
        String inputLine;
        URL site = new URL(link);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(site.openStream()));

        while ((inputLine = in.readLine()) != null)
        {
            if (inputLine.contains("courseblocktitle"))
            {
                extractCourseData(inputLine);
            }
        }
        in.close();
    }

    private static void extractCourseData(String inputLine)
    {
        Matcher match = Pattern
            .compile("([A-Z]+)&#160;(\\d+)\\. (.*)\\.")
            .matcher(inputLine);

        if (match.find())
        {
            String department = match.group(1);
            String courseNumber = match.group(2);
            String courseName = match.group(3);
            System.out.println(department + " " + courseNumber + ": " + courseName);
        }
        else
        {
            System.err.println("Parsing failed: " + inputLine);
        }
    }
}
