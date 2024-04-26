
package controllers;

        import Entities.Article;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Label;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.VBox;
        import javafx.scene.paint.Color;
        import javafx.scene.text.Text;
        import objects.Account;
        import objects.Post;
        import objects.PostAudience;
        import objects.Reactions;
        import services.ServiceArticle;

        import java.net.URL;
        import java.time.format.DateTimeFormatter;
        import java.util.ResourceBundle;

public class CommentArticleController implements Initializable {

    @FXML
    private HBox articlehadhemi;

    @FXML
    private ImageView audience;

    @FXML
    private Text categArt;

    @FXML
    private Label commenter;

    @FXML
    private HBox commenterHbox;

    @FXML
    private Text contenuart;

    @FXML
    private Label datepub;

    @FXML
    private ImageView imgAngry;

    @FXML
    private ImageView imgCare;

    @FXML
    private ImageView imgHaha;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgPost;

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgReaction;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgVerified;

    @FXML
    private ImageView imgWow;

    @FXML
    private HBox likeContainer;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbComments1;

    @FXML
    private Label nbComments11;

    @FXML
    private Label nbReactions;

    @FXML
    private Label reactionName;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private VBox reductionForm;

    @FXML
    private HBox reductionInputErrorHbox;

    @FXML
    private HBox reductionInputErrorHbox2;

    @FXML
    private Text titreArt;

    @FXML
    private Label username;
    Article article;
    private long startTime = 0;
    private Reactions currentReaction;
    @FXML
    public void onLikeContainerPressed(MouseEvent me){
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased(MouseEvent me){
        if(System.currentTimeMillis() - startTime > 500){
            reactionsContainer.setVisible(true);
        }else {
            if(reactionsContainer.isVisible()){
                reactionsContainer.setVisible(false);
            }
            if(currentReaction == Reactions.NON){
                setReaction(Reactions.LIKE);
            }else{
                setReaction(Reactions.NON);
            }
        }
    }

    @FXML
    public void onReactionImgPressed(MouseEvent me){
        switch (((ImageView) me.getSource()).getId()){
            case "imgLove":
                setReaction(Reactions.LOVE);
                break;
            case "imgCare":
                setReaction(Reactions.CARE);
                break;
            case "imgHaha":
                setReaction(Reactions.HAHA);
                break;
            case "imgWow":
                setReaction(Reactions.WOW);
                break;
            case "imgSad":
                setReaction(Reactions.SAD);
                break;
            case "imgAngry":
                setReaction(Reactions.ANGRY);
                break;
            default:
                setReaction(Reactions.LIKE);
                break;
        }
        reactionsContainer.setVisible(false);
    }

    public void setReaction(Reactions reaction){
        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        imgReaction.setImage(image);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));

        if(currentReaction == Reactions.NON){
//            article.setTotalReactions(article.getTotalReactions() + 1);
        }

        currentReaction = reaction;

        if(currentReaction == Reactions.NON){
//            article.setTotalReactions(article.getTotalReactions() - 1);
        }

        nbReactions.setText(String.valueOf(article.getTotalReactions()));
    }

    public void setData(Article article){
        this.article = article;
            ServiceArticle serviceArticle = new ServiceArticle();
            if (article != null) {
                titreArt.setText(article.getTitre_art());
                categArt.setText(article.getCategorie_art());
                if (article.getDate_pub_art() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = article.getDate_pub_art().format(formatter);
                    datepub.setText(formattedDate);
                } else {
                    datepub.setText("Date de publication non disponible");
                }
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//                String formattedDate = article.getDate_pub_art().format(formatter);
//                datepub.setText(formattedDate);
                contenuart.setText(article.getContenu_art());
            }
//        Image img;
//        img = new Image(getClass().getResourceAsStream(post.getAccount().getProfileImg()));
//        imgProfile.setImage(img);
//        username.setText(post.getAccount().getName());
//        if(post.getAccount().isVerified()){
//            imgVerified.setVisible(true);
//        }else{
//            imgVerified.setVisible(false);
//        }
//
//        date.setText(post.getDate());
//        if(post.getAudience() == PostAudience.PUBLIC){
//            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
//        }else{
//            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
//        }
//        audience.setImage(img);
//
//        if(post.getCaption() != null && !post.getCaption().isEmpty()){
//            caption.setText(post.getCaption());
//        }else{
//            caption.setManaged(false);
//        }
//
//        if(post.getImage() != null && !post.getImage().isEmpty()){
//            img = new Image(getClass().getResourceAsStream(post.getImage()));
//            imgPost.setImage(img);
//        }else{
//            imgPost.setVisible(false);
//            imgPost.setManaged(false);
//        }

//        nbReactions.setText(String.valueOf(article.getTotalReactions()));
//        nbComments.setText(article.getNbComments() + " comments");

//        nbShares.setText(article.getNbShares()+" shares");

//        currentReaction = Reactions.NON;
    }

    private Article getArticle(){
        Article post = new Article();
        Account account = new Account();
        account.setName("Mahmoud Hamwi");
        account.setProfileImg("/imagesAct/user.png");
        account.setVerified(true);
//        post.setAccount(account);
//        post.setDate("Feb 18, 2021 at 12:00 PM");
//        post.setAudience(PostAudience.PUBLIC);
//        post.setCaption("Hello everybody.");
//        post.setImage("/imagesAct/img2.jpg");
//        post.setTotalReactions(10);
//        post.setNbComments(2);
//        post.setNbShares(3);

        return post;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData(getArticle());
    }

    public void commenter(MouseEvent mouseEvent) {
    }
}