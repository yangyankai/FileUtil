# FileUtil
### Android File



// read File, 
// Use Try catch,So need not to close a closeable resource。
```
    public void fileTest() {
        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+"test.txt";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(tempPath)), "UTF8"), 1024)) {
            Log.d(TAG, "fileTest: new method" + reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
