# BrowserStack Assignment

This project demonstrates end-to-end test automation using TestNG, Selenium, and BrowserStack for cross-browser testing. The framework is designed to handle both **local execution** and **remote execution on BrowserStack**.
---

## Features

- **Page Object Model (POM)** for modular design.
- Supports both **local execution** and **remote cross-browser testing** on BrowserStack.
- Executes tests in parallel on multiple browsers/devices.
- Handles dynamic popups like cookie acceptance.
- Built-in assertions for validation.

---

## Prerequisites

Before running the project, ensure the following:

1. **Java JDK** (version 8 or higher) is installed.
2. **Maven** is installed for dependency management.
3. **BrowserStack Account** for remote execution.
4. BrowserStack configuration stored in `browserstack.propertiesl`.

---

## Installation and run

Clone this repository:

```bash
git clone https://github.com/pratik-suryawanshi/BrowserStackAssignment.git
cd BrowserStackAssignment
run mvn clean install -U

