package com.hihds.robohash4j;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
 
public class Main {
 
    public static void main(String[] args) throws Exception {
 
        // 1. Creating the server on port 9090
        Server server = new Server(9090);
        server.setAttribute("jetty.host", "0.0.0.0");
 
        // 2. Creating the WebAppContext for the created content
        WebAppContext ctx = new WebAppContext();
        ctx.setResourceBase("src/main/webapp");
        ctx.setContextPath("/");
         
        //3. Including the JSTL jars for the webapp.
        ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");
     
        //4. Enabling the Annotation based configuration
        org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");
        
        ctx.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        ctx.setInitParameter("org.eclipse.jetty.servlet.Default.maxCachedFiles", "0");
         
        //5. Setting the handler and starting the Server
        server.setHandler(ctx);
        server.start();
        server.join();
 
    }
}
