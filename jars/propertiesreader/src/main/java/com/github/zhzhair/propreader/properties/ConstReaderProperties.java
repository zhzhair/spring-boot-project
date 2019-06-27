package com.github.zhzhair.propreader.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "const.reader")
public class ConstReaderProperties {

    private Boolean dicEnable = false;
    private Boolean censorWordEnable = false;
    private String dicLocation = "data/dic.json";
    private CensorWord censorWord;

    ConstReaderProperties(){
        this.censorWord = new CensorWord();
    }

    public class CensorWord{
        private String censorWordsLocation = "data/censorWords.txt";
        private Integer wordCount = 3000;
        private boolean dfaEnabled = false;
        private boolean iteratorEnabled = true;
        public String getCensorWordsLocation() {
            return censorWordsLocation;
        }

        public void setCensorWordsLocation(String censorWordsLocation) {
            this.censorWordsLocation = censorWordsLocation;
        }

        public Integer getWordCount() {
            return wordCount;
        }

        public void setWordCount(Integer wordCount) {
            this.wordCount = wordCount;
        }

        public boolean getDfaEnabled() {
            return dfaEnabled;
        }

        public void setDfaEnabled(boolean dfaEnabled) {
            this.dfaEnabled = dfaEnabled;
        }

        public boolean getIteratorEnabled() {
            return iteratorEnabled;
        }

        public void setIteratorEnabled(boolean iteratorEnabled) {
            this.iteratorEnabled = iteratorEnabled;
        }
    }

    public String getDicLocation() {
        return dicLocation;
    }

    public void setDicLocation(String dicLocation) {
        this.dicLocation = dicLocation;
    }

    public Boolean getDicEnable() {
        return dicEnable;
    }

    public void setDicEnable(Boolean dicEnable) {
        this.dicEnable = dicEnable;
    }

    public Boolean getCensorWordEnable() {
        return censorWordEnable;
    }

    public void setCensorWordEnable(Boolean censorWordEnable) {
        this.censorWordEnable = censorWordEnable;
    }

    public CensorWord getCensorWord() {
        return censorWord;
    }

    public void setCensorWord(CensorWord censorWord) {
        this.censorWord = censorWord;
    }
}
