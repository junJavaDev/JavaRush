import java.io.FileInputStream;

class FilePrinter
{
    public String content;

    public FilePrinter(String filename) throws Exception
    {
        FileInputStream input = new FileInputStream(filename);
        byte[] buffer = input.readAllBytes();
        this.content = new String(buffer);
    }

    public void printFile()
    {
        System.out.println(content);
    }
}

class Solution
{
    FilePrinter filePrinter = new FilePrinter("Name");

    Solution() throws Exception {
    }
}