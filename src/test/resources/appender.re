   <appender name="myConsole" class="org.apache.log4j.ConsoleAppender">  
        <layout class="org.apache.log4j.PatternLayout">  
            <param name="ConversionPattern"    
                value="[%d{dd HH:mm:ss,SSS\} %-5p] [%t] %c{2\} - %m%n" />  
        </layout>  
        <!--过滤器设置输出的级别-->  
        <filter class="org.apache.log4j.varia.LevelRangeFilter">  
            <param name="levelMin" value="debug" />  
            <param name="levelMax" value="warn" />  
            <param name="AcceptOnMatch" value="true" />  
        </filter>  
    </appender>  
  
    <appender name="myFile" class="org.apache.log4j.RollingFileAppender">    
        <param name="File" value="D:/output.log" /><!-- 设置日志输出文件名 -->  
        <!-- 设置是否在重新启动服务时，在原有日志的基础添加新日志 -->  
        <param name="Append" value="true" />  
        <param name="MaxBackupIndex" value="10" />  
        <layout class="org.apache.log4j.PatternLayout">  
            <param name="ConversionPattern" value="%p (%c:%L)- %m%n" />  
        </layout>  
    </appender>  

    
     <!-- 根logger的设置-->  
    <root>  
        <priority value ="debug"/>  
        <appender-ref ref="myConsole"/>  
        <appender-ref ref="myFile"/>    
    </root>  
</log4j:configuration> 