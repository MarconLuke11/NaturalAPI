/**
 * @file main
 * @version 1.0.0
 * @type java
 * @data 2020-05-01
 * @author Jacopo Battilana
 * @email hexatech016@gmail.com
 * @license
 * @changeLog
 */

package com.hexaTech;

import com.hexaTech.client.CLI;
import com.hexaTech.controllerPresenter.*;
import com.hexaTech.interactor.*;
import com.hexaTech.portInterface.AddBALInputPort;
import com.hexaTech.portInterface.AddPLAInputPort;
import com.hexaTech.portInterface.CreateAPIInputPort;
import com.hexaTech.repo.*;
import com.hexaTech.repointerface.*;
import net.didion.jwnl.JWNLException;

import java.io.IOException;

/**
 * Class used to manage the flow of program execution.
 */
public class Main{
    public static void main(String[] args) throws IOException, JWNLException {

        //PRESENTERS
        Presenter presenter=new Presenter();
        //REPOS
        WordNetFramework wordNetFramework=new WordNetFramework();
            //DISCOVER
        RepoDocumentInterface repoDocument=new RepoDocument();
        RepoBDLInterface repoBDLInterface=new RepoBDL();
            //DESIGN
        RepoBDL repoBDL=new RepoBDL();
        RepoGherkin repoGherkin=new RepoGherkin();
        RepoBO repoBO=new RepoBO();
            //DEVELOP
        RepoAPIInterface repoAPI=new RepoAPI();
        RepoBALInterface repoBAL=new RepoBAL();
        RepoBALDocumentInterface repoBALDocument=new RepoBALDocument();
        RepoPLAInterface repoPLA=new RepoPLA();
        //INTERACTORS
            //DISCOVER
        AddDocument addDocument =new AddDocument(presenter,repoDocument);
        CreateBDL createBDL=new CreateBDL(presenter,repoBDLInterface,repoDocument);
        CheckBetweenBDLAndGherkin checkBetweenBDLAndGherkin=new CheckBetweenBDLAndGherkin(repoBDLInterface,repoGherkin,wordNetFramework);
            //DESIGN
        AddGherkin addGherkin=new AddGherkin(presenter,repoGherkin);
        AddBDL addBDL=new AddBDL(presenter,repoBDL);
        AddBO addBO=new AddBO(presenter, repoBO);
        CreateBO createBO= new CreateBO(presenter, repoBO);
        CreateBAL createBAL=new CreateBAL(presenter,repoGherkin, repoBALDocument, repoBAL, repoBO, repoBDL);
            //DEVELOP
        AddPLAInputPort addPLA=new AddPLA(presenter,repoPLA);
        AddBALInputPort addBAL=new AddBAL(presenter, repoBALDocument);
        CreateAPIInputPort createAPI=new CreateAPI(presenter,repoPLA, repoBALDocument,repoAPI);
        //CONTROLLERS
        ControllerDiscover controllerDiscover=new ControllerDiscover(addDocument,createBDL,checkBetweenBDLAndGherkin);
        ControllerDesign controllerDesign=new ControllerDesign(addBDL,addGherkin,createBAL, addBO, createBO);
        ControllerDevelop controllerDevelop=new ControllerDevelop(addPLA,addBAL,createAPI);
        //CLIENT
        CLI client=new CLI(controllerDiscover,controllerDesign,controllerDevelop,presenter);
        client.useCaseNaturalAPI();
    }//main
}//MainDevelop
