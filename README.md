# DigitalOceanApp 

[![Build Status](https://travis-ci.org/coding-blocks/DigitalOceanApp.svg?branch=master)](https://travis-ci.org/coding-blocks/DigitalOceanApp)
[![CircleCI](https://circleci.com/gh/coding-blocks/DigitalOceanApp.svg?style=shield)](https://circleci.com/gh/coding-blocks/DigitalOceanApp)
[![codecov](https://codecov.io/gh/coding-blocks/DigitalOceanApp/branch/master/graph/badge.svg)](https://codecov.io/gh/coding-blocks/DigitalOceanApp)
[![codebeat badge](https://codebeat.co/badges/39a67587-5c32-416f-8166-3a5eb43335af)](https://codebeat.co/projects/github-com-coding-blocks-digitaloceanapp-master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ab89062fe8b3484485edd8babd04696a)](https://www.codacy.com/app/championswimmer/DigitalOceanApp?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=coding-blocks/DigitalOceanApp&amp;utm_campaign=Badge_Grade)
[![Code Climate](https://codeclimate.com/github/coding-blocks/DigitalOceanApp/badges/gpa.svg)](https://codeclimate.com/github/coding-blocks/DigitalOceanApp)

<a href='https://play.google.com/store/apps/details?id=in.tosc.digitaloceanapp&hl=en&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img height=80 alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'/></a>

[Unofficial] Android App and Android Library for accessing the DigitalOcean API.
Originally made as a part of DigitalOcean CloudHackathon.

The library provides easy to use Java methods to make API calls to DigitalOcean.

The Android App lets people sign in with their Digital Ocean account. 
Once signed in, you can see your droplets, and their status. 
It has features such as - 
 - powering off / rebooting your droplet
 - turning backups on/off
 - taking snapshot of droplet
 - resizing droplet
 - creating a droplet

 ## Preview

 ![preview](docs/preview2.gif)
 
 ### Automating Publishing to the Play Store
 
- The first APK or App Bundle needs to be uploaded via the Google Play Console because registering the app with the Play Store cannot be done using the Play Developer API.
- To use this plugin, you must create a service account with access to the Play Developer API. Once that's done, you'll need to grant the following permissions to your service account for this plugin to work (go to Settings -> Developer account -> API access -> Service Accounts).
- Once done download your PKCS12 key or json key somewhere and the location of key in the build.gradle file in the play block
- Then run one of the following commands:

   | Command | Description |
   | ------------- | ------------- |
   | 'publishApkRelease'| Uploads the APK and the summary of recent changes. |
   | 'publishListingRelease'| Uploads the descriptions and images for the Play Store listing.|
   | 'publishRelease'| Uploads everything.|
   | 'bootstrapReleasePlayResources'| Fetch data from the Play Store & bootstrap the required files/folders.|

                                
You can now type the following gradle commands such as the following:

bash
./gradlew publishApkRelease
                                

