package in.tosc.doandroidlib.mockapi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by championswimmer on 15/07/17.
 */

public class MockResponses {

    public static final HashMap<String, String> api = new HashMap<>();
    static {
        api.put("account",
                "{\n" +
                        "  \"account\": {\n" +
                        "    \"droplet_limit\": 25,\n" +
                        "    \"floating_ip_limit\": 5,\n" +
                        "    \"email\": \"sammy@digitalocean.com\",\n" +
                        "    \"uuid\": \"b6fr89dbf6d9156cace5f3c78dc9851d957381ef\",\n" +
                        "    \"email_verified\": true,\n" +
                        "    \"status\": \"active\",\n" +
                        "    \"status_message\": \"\"\n" +
                        "  }\n" +
                        "}"
        );
        api.put("droplets",
                "{\n" +
                        "  \"droplets\": [\n" +
                        "    {\n" +
                        "      \"id\": 3164444,\n" +
                        "      \"name\": \"example.com\",\n" +
                        "      \"memory\": 512,\n" +
                        "      \"vcpus\": 1,\n" +
                        "      \"disk\": 20,\n" +
                        "      \"locked\": false,\n" +
                        "      \"status\": \"active\",\n" +
                        "      \"kernel\": {\n" +
                        "        \"id\": 2233,\n" +
                        "        \"name\": \"Ubuntu 14.04 x64 vmlinuz-3.13.0-37-generic\",\n" +
                        "        \"version\": \"3.13.0-37-generic\"\n" +
                        "      },\n" +
                        "      \"created_at\": \"2014-11-14T16:29:21Z\",\n" +
                        "      \"features\": [\n" +
                        "        \"backups\",\n" +
                        "        \"ipv6\",\n" +
                        "        \"virtio\"\n" +
                        "      ],\n" +
                        "      \"backup_ids\": [\n" +
                        "        7938002\n" +
                        "      ],\n" +
                        "      \"snapshot_ids\": [\n" +
                        "\n" +
                        "      ],\n" +
                        "      \"image\": {\n" +
                        "        \"id\": 6918990,\n" +
                        "        \"name\": \"14.04 x64\",\n" +
                        "        \"distribution\": \"Ubuntu\",\n" +
                        "        \"slug\": \"ubuntu-14-04-x64\",\n" +
                        "        \"public\": true,\n" +
                        "        \"regions\": [\n" +
                        "          \"nyc1\",\n" +
                        "          \"ams1\",\n" +
                        "          \"sfo1\",\n" +
                        "          \"nyc2\",\n" +
                        "          \"ams2\",\n" +
                        "          \"sgp1\",\n" +
                        "          \"lon1\",\n" +
                        "          \"nyc3\",\n" +
                        "          \"ams3\",\n" +
                        "          \"nyc3\"\n" +
                        "        ],\n" +
                        "        \"created_at\": \"2014-10-17T20:24:33Z\",\n" +
                        "        \"type\": \"snapshot\",\n" +
                        "        \"min_disk_size\": 20,\n" +
                        "        \"size_gigabytes\": 2.34\n" +
                        "      },\n" +
                        "      \"volume_ids\": [\n" +
                        "\n" +
                        "      ],\n" +
                        "      \"size\": {\n" +
                        "      },\n" +
                        "      \"size_slug\": \"512mb\",\n" +
                        "      \"networks\": {\n" +
                        "        \"v4\": [\n" +
                        "          {\n" +
                        "            \"ip_address\": \"104.236.32.182\",\n" +
                        "            \"netmask\": \"255.255.192.0\",\n" +
                        "            \"gateway\": \"104.236.0.1\",\n" +
                        "            \"type\": \"public\"\n" +
                        "          }\n" +
                        "        ],\n" +
                        "        \"v6\": [\n" +
                        "          {\n" +
                        "            \"ip_address\": \"2604:A880:0800:0010:0000:0000:02DD:4001\",\n" +
                        "            \"netmask\": 64,\n" +
                        "            \"gateway\": \"2604:A880:0800:0010:0000:0000:0000:0001\",\n" +
                        "            \"type\": \"public\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      \"region\": {\n" +
                        "        \"name\": \"New York 3\",\n" +
                        "        \"slug\": \"nyc3\",\n" +
                        "        \"sizes\": [\n" +
                        "\n" +
                        "        ],\n" +
                        "        \"features\": [\n" +
                        "          \"virtio\",\n" +
                        "          \"private_networking\",\n" +
                        "          \"backups\",\n" +
                        "          \"ipv6\",\n" +
                        "          \"metadata\"\n" +
                        "        ],\n" +
                        "        \"available\": null\n" +
                        "      },\n" +
                        "      \"tags\": [\n" +
                        "\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"links\": {\n" +
                        "    \"pages\": {\n" +
                        "      \"last\": \"https://api.digitalocean.com/v2/droplets?page=3&per_page=1\",\n" +
                        "      \"next\": \"https://api.digitalocean.com/v2/droplets?page=2&per_page=1\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"meta\": {\n" +
                        "    \"total\": 3\n" +
                        "  }\n" +
                        "}"
        );
        api.put("sizes",
                "{\n" +
                        "  \"sizes\": [\n" +
                        "    {\n" +
                        "      \"slug\": \"512mb\",\n" +
                        "      \"memory\": 512,\n" +
                        "      \"vcpus\": 1,\n" +
                        "      \"disk\": 20,\n" +
                        "      \"transfer\": 1.0,\n" +
                        "      \"price_monthly\": 5.0,\n" +
                        "      \"price_hourly\": 0.00744,\n" +
                        "      \"regions\": [\n" +
                        "        \"nyc1\",\n" +
                        "        \"sgp1\",\n" +
                        "        \"ams1\",\n" +
                        "        \"ams2\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"nyc2\",\n" +
                        "        \"lon1\",\n" +
                        "        \"nyc3\",\n" +
                        "        \"ams3\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"slug\": \"1gb\",\n" +
                        "      \"memory\": 1024,\n" +
                        "      \"vcpus\": 1,\n" +
                        "      \"disk\": 30,\n" +
                        "      \"transfer\": 2.0,\n" +
                        "      \"price_monthly\": 10.0,\n" +
                        "      \"price_hourly\": 0.01488,\n" +
                        "      \"regions\": [\n" +
                        "        \"nyc2\",\n" +
                        "        \"sgp1\",\n" +
                        "        \"ams1\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"lon1\",\n" +
                        "        \"nyc3\",\n" +
                        "        \"ams3\",\n" +
                        "        \"ams2\",\n" +
                        "        \"nyc1\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"slug\": \"2gb\",\n" +
                        "      \"memory\": 2048,\n" +
                        "      \"vcpus\": 2,\n" +
                        "      \"disk\": 40,\n" +
                        "      \"transfer\": 3.0,\n" +
                        "      \"price_monthly\": 20.0,\n" +
                        "      \"price_hourly\": 0.02976,\n" +
                        "      \"regions\": [\n" +
                        "        \"nyc2\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"ams1\",\n" +
                        "        \"sgp1\",\n" +
                        "        \"ams2\",\n" +
                        "        \"lon1\",\n" +
                        "        \"nyc3\",\n" +
                        "        \"ams3\",\n" +
                        "        \"nyc1\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"slug\": \"4gb\",\n" +
                        "      \"memory\": 4096,\n" +
                        "      \"vcpus\": 2,\n" +
                        "      \"disk\": 60,\n" +
                        "      \"transfer\": 4.0,\n" +
                        "      \"price_monthly\": 40.0,\n" +
                        "      \"price_hourly\": 0.05952,\n" +
                        "      \"regions\": [\n" +
                        "        \"nyc2\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"ams1\",\n" +
                        "        \"sgp1\",\n" +
                        "        \"ams2\",\n" +
                        "        \"lon1\",\n" +
                        "        \"nyc3\",\n" +
                        "        \"ams3\",\n" +
                        "        \"nyc1\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"slug\": \"8gb\",\n" +
                        "      \"memory\": 8192,\n" +
                        "      \"vcpus\": 4,\n" +
                        "      \"disk\": 80,\n" +
                        "      \"transfer\": 5.0,\n" +
                        "      \"price_monthly\": 80.0,\n" +
                        "      \"price_hourly\": 0.11905,\n" +
                        "      \"regions\": [\n" +
                        "        \"nyc2\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"sgp1\",\n" +
                        "        \"ams1\",\n" +
                        "        \"ams2\",\n" +
                        "        \"nyc1\",\n" +
                        "        \"lon1\",\n" +
                        "        \"nyc3\",\n" +
                        "        \"ams3\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"slug\": \"16gb\",\n" +
                        "      \"memory\": 16384,\n" +
                        "      \"vcpus\": 8,\n" +
                        "      \"disk\": 160,\n" +
                        "      \"transfer\": 6.0,\n" +
                        "      \"price_monthly\": 160.0,\n" +
                        "      \"price_hourly\": 0.2381,\n" +
                        "      \"regions\": [\n" +
                        "        \"sgp1\",\n" +
                        "        \"nyc1\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"ams2\",\n" +
                        "        \"nyc3\",\n" +
                        "        \"lon1\",\n" +
                        "        \"nyc2\",\n" +
                        "        \"ams1\",\n" +
                        "        \"ams3\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"slug\": \"32gb\",\n" +
                        "      \"memory\": 32768,\n" +
                        "      \"vcpus\": 12,\n" +
                        "      \"disk\": 320,\n" +
                        "      \"transfer\": 7.0,\n" +
                        "      \"price_monthly\": 320.0,\n" +
                        "      \"price_hourly\": 0.47619,\n" +
                        "      \"regions\": [\n" +
                        "        \"nyc2\",\n" +
                        "        \"sgp1\",\n" +
                        "        \"ams2\",\n" +
                        "        \"nyc1\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"lon1\",\n" +
                        "        \"ams3\",\n" +
                        "        \"nyc3\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"slug\": \"48gb\",\n" +
                        "      \"memory\": 49152,\n" +
                        "      \"vcpus\": 16,\n" +
                        "      \"disk\": 480,\n" +
                        "      \"transfer\": 8.0,\n" +
                        "      \"price_monthly\": 480.0,\n" +
                        "      \"price_hourly\": 0.71429,\n" +
                        "      \"regions\": [\n" +
                        "        \"sgp1\",\n" +
                        "        \"ams2\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"nyc1\",\n" +
                        "        \"lon1\",\n" +
                        "        \"nyc2\",\n" +
                        "        \"ams3\",\n" +
                        "        \"nyc3\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"slug\": \"64gb\",\n" +
                        "      \"memory\": 65536,\n" +
                        "      \"vcpus\": 20,\n" +
                        "      \"disk\": 640,\n" +
                        "      \"transfer\": 9.0,\n" +
                        "      \"price_monthly\": 640.0,\n" +
                        "      \"price_hourly\": 0.95238,\n" +
                        "      \"regions\": [\n" +
                        "        \"sgp1\",\n" +
                        "        \"nyc1\",\n" +
                        "        \"nyc2\",\n" +
                        "        \"sfo1\",\n" +
                        "        \"lon1\",\n" +
                        "        \"ams3\",\n" +
                        "        \"ams2\",\n" +
                        "        \"nyc3\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"links\": {\n" +
                        "  },\n" +
                        "  \"meta\": {\n" +
                        "    \"total\": 9\n" +
                        "  }\n" +
                        "}"
        );

        api.put("images",
                "{\n" +
                        "  \"images\": [\n" +
                        "    {\n" +
                        "      \"id\": 7555620,\n" +
                        "      \"name\": \"Nifty New Snapshot\",\n" +
                        "      \"distribution\": \"Ubuntu\",\n" +
                        "      \"slug\": null,\n" +
                        "      \"public\": false,\n" +
                        "      \"regions\": [\n" +
                        "        \"nyc2\",\n" +
                        "        \"nyc2\"\n" +
                        "      ],\n" +
                        "      \"created_at\": \"2014-11-04T22:23:02Z\",\n" +
                        "      \"type\": \"snapshot\",\n" +
                        "      \"min_disk_size\": 20,\n" +
                        "      \"size_gigabytes\": 2.34\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"links\": {\n" +
                        "    \"pages\": {\n" +
                        "      \"last\": \"https://api.digitalocean.com/v2/images?page=56&per_page=1\",\n" +
                        "      \"next\": \"https://api.digitalocean.com/v2/images?page=2&per_page=1\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"meta\": {\n" +
                        "    \"total\": 56\n" +
                        "  }\n" +
                        "}"
        );

        api.put("regions",
                "{\n" +
                        "  \"regions\": [\n" +
                        "    {\n" +
                        "      \"name\": \"New York 1\",\n" +
                        "      \"slug\": \"nyc1\",\n" +
                        "      \"sizes\": [\n" +
                        "\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"backups\"\n" +
                        "      ],\n" +
                        "      \"available\": false\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"Amsterdam 1\",\n" +
                        "      \"slug\": \"ams1\",\n" +
                        "      \"sizes\": [\n" +
                        "\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"backups\"\n" +
                        "      ],\n" +
                        "      \"available\": false\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"San Francisco 1\",\n" +
                        "      \"slug\": \"sfo1\",\n" +
                        "      \"sizes\": [\n" +
                        "        \"32gb\",\n" +
                        "        \"16gb\",\n" +
                        "        \"2gb\",\n" +
                        "        \"1gb\",\n" +
                        "        \"4gb\",\n" +
                        "        \"8gb\",\n" +
                        "        \"512mb\",\n" +
                        "        \"64gb\",\n" +
                        "        \"48gb\"\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"backups\",\n" +
                        "        \"metadata\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"New York 2\",\n" +
                        "      \"slug\": \"nyc2\",\n" +
                        "      \"sizes\": [\n" +
                        "        \"32gb\",\n" +
                        "        \"16gb\",\n" +
                        "        \"2gb\",\n" +
                        "        \"1gb\",\n" +
                        "        \"4gb\",\n" +
                        "        \"8gb\",\n" +
                        "        \"512mb\",\n" +
                        "        \"64gb\",\n" +
                        "        \"48gb\"\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"private_networking\",\n" +
                        "        \"backups\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"Amsterdam 2\",\n" +
                        "      \"slug\": \"ams2\",\n" +
                        "      \"sizes\": [\n" +
                        "        \"32gb\",\n" +
                        "        \"16gb\",\n" +
                        "        \"2gb\",\n" +
                        "        \"1gb\",\n" +
                        "        \"4gb\",\n" +
                        "        \"8gb\",\n" +
                        "        \"512mb\",\n" +
                        "        \"64gb\",\n" +
                        "        \"48gb\"\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"private_networking\",\n" +
                        "        \"backups\",\n" +
                        "        \"metadata\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"Singapore 1\",\n" +
                        "      \"slug\": \"sgp1\",\n" +
                        "      \"sizes\": [\n" +
                        "        \"32gb\",\n" +
                        "        \"16gb\",\n" +
                        "        \"2gb\",\n" +
                        "        \"1gb\",\n" +
                        "        \"4gb\",\n" +
                        "        \"8gb\",\n" +
                        "        \"512mb\",\n" +
                        "        \"64gb\",\n" +
                        "        \"48gb\"\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"private_networking\",\n" +
                        "        \"backups\",\n" +
                        "        \"ipv6\",\n" +
                        "        \"metadata\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"London 1\",\n" +
                        "      \"slug\": \"lon1\",\n" +
                        "      \"sizes\": [\n" +
                        "        \"32gb\",\n" +
                        "        \"16gb\",\n" +
                        "        \"2gb\",\n" +
                        "        \"1gb\",\n" +
                        "        \"4gb\",\n" +
                        "        \"8gb\",\n" +
                        "        \"512mb\",\n" +
                        "        \"64gb\",\n" +
                        "        \"48gb\"\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"private_networking\",\n" +
                        "        \"backups\",\n" +
                        "        \"ipv6\",\n" +
                        "        \"metadata\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"New York 3\",\n" +
                        "      \"slug\": \"nyc3\",\n" +
                        "      \"sizes\": [\n" +
                        "        \"32gb\",\n" +
                        "        \"16gb\",\n" +
                        "        \"2gb\",\n" +
                        "        \"1gb\",\n" +
                        "        \"4gb\",\n" +
                        "        \"8gb\",\n" +
                        "        \"512mb\",\n" +
                        "        \"64gb\",\n" +
                        "        \"48gb\"\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"private_networking\",\n" +
                        "        \"backups\",\n" +
                        "        \"ipv6\",\n" +
                        "        \"metadata\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"Amsterdam 3\",\n" +
                        "      \"slug\": \"ams3\",\n" +
                        "      \"sizes\": [\n" +
                        "        \"32gb\",\n" +
                        "        \"16gb\",\n" +
                        "        \"2gb\",\n" +
                        "        \"1gb\",\n" +
                        "        \"4gb\",\n" +
                        "        \"8gb\",\n" +
                        "        \"512mb\",\n" +
                        "        \"64gb\",\n" +
                        "        \"48gb\"\n" +
                        "      ],\n" +
                        "      \"features\": [\n" +
                        "        \"virtio\",\n" +
                        "        \"private_networking\",\n" +
                        "        \"backups\",\n" +
                        "        \"ipv6\",\n" +
                        "        \"metadata\"\n" +
                        "      ],\n" +
                        "      \"available\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"links\": {\n" +
                        "  },\n" +
                        "  \"meta\": {\n" +
                        "    \"total\": 9\n" +
                        "  }\n" +
                        "}"
        );
    }

}
