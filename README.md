# TEMPLATE FRAMEWORK

## Description

## Quickstart

Assuming Google Chrome and Git installed:

```bash
# install java 11 (skip if already installed)
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk11

# install chromedriver (skip if already installed)
brew cask install chromedriver

# clone the project and launch the tests
git clone git@github.com:barranquerox/template-framework.git
cd template-framework
./gradlew test -Dbrowser=chrome
```

This will execute all the tests using your local Chrome browser.
