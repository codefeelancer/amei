package abr.bean;

import lombok.Data;

/**
 * @author Y.H.
 * @create 2017-11-21 21:33
 **/
@Data
public class Webpage {

    private String title;     //页面的title
    private String path;      //相对于根目录"/"的位置，比如说/product的path="product"
    private String html;      //页面除了header和footer的html源代码

}
