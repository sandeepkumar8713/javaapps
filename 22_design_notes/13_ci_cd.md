# CI/CD

Medium : https://medium.com/devops-dudes/what-is-ci-cd-continuous-integration-continuous-delivery-in-2020-988765f5d116

## Continues Integration : 
1. Continuous integration (CI) helps to ensure that **software components are working together**. Integration should be \
   completed frequently; on an hourly or daily basis, if possible.
2. Typically, the CI pipeline involves the following tasks:
    1. Detect changes to the source code repository (new commits appear)
    2. Analysis of the quality of source code
    3. Project build
    4. Perform all unit tests
    5. Run all integration tests
    6. Generate deployable artifacts
    7. Status of report
3. If one of the steps above fails:
    1. Integration may stop or continue depending on the severity and configuration of the defect.
    2. Results are reported to the team via **email or chat system**.
    3. Team fixes the defect and commits it again
    4. Tasks are carried out again

## Continues Delivery : 
1. Continuous delivery (CD) picks up where continuous integration is over. While CI is the process to build and test \
   automatically, CD deploys all code changes to the testing or staging environment in the build.
2. Typically, the CD pipeline involves the following tasks:
    1. Version Control
    2. Build
    3. Unit Test
    4. Integration Test
    5. Deploy on test server
    6. Run automatic test in test server
    7. Deploy to production
    8. Measure and validate 
3. The constant feedback loop helps make the pipeline a closed process where builds are continuously committed, \
   tested, and deployed to production.

## Testing :
For example, we can address the following types of tests:
1. API testing
2. Load testing
3. UI testing
4. Regression testing
5. Unit and component testing
6. Functional testing
7. Nonfunctional testing
8. Cross-browser testing

## Typical dev cycle for a new feature
- Development 
    - spend hours & hours to write code. Test it locally. Write comments. Ensure all commits are good. 
    - Finally submit a Pull Request (PR)
- Team - review the PR, ask you to make changes/improvements - iterations ... merged
- QA team - write new tests, integration tests
- Deployment
    + Staging servers - monitoring / tests/ metrics
    + Canary deployments / AB deployments
        * deployed to 5% of the user base
            - are there new exceptions
            - are the people complaining
            - have the ratings gone down
        * finally deploy the code
