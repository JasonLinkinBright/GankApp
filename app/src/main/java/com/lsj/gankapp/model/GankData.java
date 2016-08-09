package com.lsj.gankapp.model;

import java.util.List;

/**
 * ClassName: GankData
 * Desc:
 * Created by lsj on 16/7/28.
 */

public class GankData {
    private boolean error;
    private List<Gank> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Gank> getResults() {
        return results;
    }

    public void setResults(List<Gank> results) {
        this.results = results;
    }
}
