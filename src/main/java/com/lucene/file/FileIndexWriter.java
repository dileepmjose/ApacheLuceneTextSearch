package com.lucene.file;

import java.io.IOException;

public class FileIndexWriter {
	
   String indexDir = "index";
   String dataDir = "inputFile";
   Indexer indexer;
   
   public static void main(String[] args) {
      FileIndexWriter tester;
      try {
         tester = new FileIndexWriter();
         tester.createIndex();
      } catch (IOException e) {
         e.printStackTrace();
      } 
   }

   private void createIndex() throws IOException {
      indexer = new Indexer(indexDir);
      int numIndexed;
      long startTime = System.currentTimeMillis();	
      numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
      long endTime = System.currentTimeMillis();
      indexer.close();
      System.out.println(numIndexed+" File indexed, time taken: "
         +(endTime-startTime)+" ms");		
   }
}