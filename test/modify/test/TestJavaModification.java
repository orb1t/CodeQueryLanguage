package modify.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

import modify.TreeOperation;

public class TestJavaModification {

	@Test
	public void testJavaModification() {
		
/* reset the data */
		
		{
			File testFile = new File("src/modify/test/Foo.java");
			
			if (!testFile.exists()) {
				try {
					testFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			RandomAccessFile file = null;
			try {
				file = new RandomAccessFile(testFile, "rw");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			
			try {
				file.setLength(0L);
				file.write((""
						 + "package modify.test;\n"
						 + "public class Foo {\n"
						 + "\n"
						 + "\tpublic static void bar(String[] args) {\n"
						 + "\n"
						 + "\t}\n"
						 + "}").getBytes());
				file.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(-1);
			}
		}
		
		for (int i = 0 ; i < 20 ; i ++) {
			
			System.out.println(i);
			
			if (i == 3) {
				
				System.out.print("");
			}
			
			TreeOperation data = new TreeOperation("src/modify/test/Foo.java")
					.into("public/class/.*/{public/static/void/.*/.*/.*}")
					.insert("System.out.println(\"Hello, world!\");\n");
		}
		
		System.out.print("");
	}
}
