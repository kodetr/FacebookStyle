/*
 * Copyright (c) 2016. Tanwir. All Rights Reserver.
 * <p>
 * Save to the extent permitted by law, you may not use,copy,modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Tanwir.
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package com.codetr.tanwir.colorpiker;

/**
 * Created by Tanwir on 06/09/2017.
 */
public class Comments {

    private String name;
    private String code;

    public Comments(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
