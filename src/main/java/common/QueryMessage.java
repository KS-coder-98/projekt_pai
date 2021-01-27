package common;

import client.view.UI;
import org.w3c.dom.NodeList;
import server.model.xmlSearch.XPathBookService;
import server.service.communication.Send;
import settings.Setting;

import java.util.List;

public class QueryMessage extends Message {
    public QueryMessage(String login, String Password, String name, String surname, String mail, String controlQuestion, String answerControlQuestion, String searchedPhrase, Sender sender, Status status, String newPassword, List<String> bookLists, String query) {
        super(login, Password, name, surname, mail, controlQuestion, answerControlQuestion, searchedPhrase, sender, status, newPassword, bookLists, query);
    }

    @Override
    public void processing(Send send) {
        if (getSender() == Sender.Client) {
            XPathBookService xPathBookService = new XPathBookService(Setting.databaseBooks);
            QueryMessage queryMessage = null;
            if (getStatus() == Status.FIND_BY_AUTHOR_REQUEST) {
                String shearedAuthor = getQuery();
                List<String> nodeList = xPathBookService.getBooksByAuthor(shearedAuthor);
                queryMessage = new QueryMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.FIND_BY_AUTHOR_RESPONSE, null, nodeList, null);
            } else if (getStatus() == Status.FIND_BY_YEAR_REQUEST) {
                int shearedYear = Integer.parseInt(getQuery());
                List<String> nodeList = xPathBookService.getBooksByYear(shearedYear);
                queryMessage = new QueryMessage(null, null, null, null, null, null, null, null, Sender.Server, Status.FIND_BY_YEAR_RESPONSE, null, nodeList, null);
            }
            send.addMessageToQueue(queryMessage);
        } else if (getSender() == Sender.Server) {
            if (getStatus() == Status.FIND_BY_AUTHOR_RESPONSE || getStatus() == Status.FIND_BY_YEAR_RESPONSE) {
                UI.findBySth(getBookLists());
            }
        }
    }
}
