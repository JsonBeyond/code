package com.alex.arithmatic.dfa;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SensitiveWordService
 * @Description TODO
 * @Author Alex
 * @CreateDate 31/07/2019 14:45
 * @Version 1.0
 */
public class SensitiveWordService {

    public Set<String> sensitiveWordFiltering(String text) {
        // 初始化敏感词库对象
        SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
        // 从数据库中获取敏感词对象集合（调用的方法来自Dao层，此方法是service层的实现类）
//        List<String> sensitiveWords = sensitiveWordDao.getSensitiveWordListAll();
        List<String> sensitiveWords = Arrays.asList("王八", "翻墙", "王八蛋");
        // 构建敏感词库
        // 传入SensitivewordEngine类中的敏感词库
        SensitivewordEngine.sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWords);
        // 得到敏感词有哪些，传入2表示获取所有敏感词，1表示获取最小的敏感词，例如“王八蛋”不会查出来
        Set<String> set = SensitivewordEngine.getSensitiveWord(text, 2);
        return set;
    }
}
