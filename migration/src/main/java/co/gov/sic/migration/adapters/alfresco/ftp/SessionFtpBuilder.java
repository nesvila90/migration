package co.gov.sic.migration.adapters.alfresco.ftp;

import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;


public class SessionFtpBuilder {
/*
    private static final String ALFRESCO_HOST_IP = "alfresco.host";
    private static final String USER_ALFRESCO_SERVER_HOST = "host.user";
    private static final String PASSWORD_ALFRESCO_HOST = "host.password";
    private static final String PROTOCOL = "sftp";

    private static SessionFtpBuilder sessionBuilder;
    private Session session;
    private JSch sftpClient;
    private ChannelSftp sftpChannel;

    private SessionFtpBuilder() {}

    public static synchronized SessionFtpBuilder getInstance(){
        if (sessionBuilder == null) {
            sessionBuilder = new SessionFtpBuilder();
        }
        return sessionBuilder;
    }


    private void createSession() throws JSchException {
        sftpClient = new JSch();
        session = sftpClient.getSession(
                AlfrescoBundleUtil.getProperty(USER_ALFRESCO_SERVER_HOST),
                AlfrescoBundleUtil.getProperty(ALFRESCO_HOST_IP));
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(AlfrescoBundleUtil.getProperty(PASSWORD_ALFRESCO_HOST));
        session.connect();
    }

    private ChannelSftp createSftpChannel(Session session) throws JSchException {
        this.sftpChannel = (ChannelSftp) session.openChannel(PROTOCOL);
        sftpChannel.connect();
        return sftpChannel;
    }

    public final ChannelSftp createClient() throws JSchException {
        createSession();
        this.sftpChannel = createSftpChannel(session);
        return sftpChannel;
    }

    public void destroySession(){
        this.session = null;
    }

    public void destroyClient(){
        this.session.disconnect();
        this.sftpChannel.disconnect();
        sessionBuilder =null;
    }
    */
}
