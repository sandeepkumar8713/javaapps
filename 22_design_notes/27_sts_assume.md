## STS

**access key, access id and secret token** of your account

Link : https://stackoverflow.com/questions/21956794/aws-assumerole-user-is-not-authorized-to-perform-stsassumerole-on-resource/33850060#33850060

https://www.youtube.com/watch?v=_ZCTvmaPgao
https://www.youtube.com/watch?v=hVo3JBDu7hI

Good Link: https://repost.aws/knowledge-center/iam-assume-role-cli

**Allow self-assume for STS in TF**

```terraform
resource "aws_iam_role_policy" "ecs_task_sts" {
  role   = module.aws_ecs_service_backend.iam_role
  policy = data.aws_iam_policy_document.ecs_task_sts.json
}

data "aws_iam_policy_document" "ecs_task_sts" {
  statement {
    effect    = "Allow"
    actions   = ["sts:AssumeRole"]
    resources = [module.aws_ecs_service_backend.iam_role_arn]
  }
}
```

```shell
aws sts assume-role --role-arn "arn:aws:iam::756570553573:role/loan-monitor-backend-ecs-task" --role-session-name AWSCLI-Session

An error occurred (AccessDenied) when calling the AssumeRole operation: User: arn:aws:sts::756570553573:assumed-role/AWSReservedSSO_AWSEngineerAccessRole_672b56bdc62ef609/Sandeep.Kumar@oaknorth.co.uk
is not authorized to perform: sts:AssumeRole on resource: arn:aws:iam::756570553573:role/loan-monitor-backend-ecs-task
```

**Problem**

```python
aws_credentials = boto3.Session().get_credentials().get_frozen_credentials()
```

**Solution**

```python
def refresh_credentials() -> Dict[str, str]:
    role_arn = os.environ.get("IAM_ROLE_ARN")
    logger.info(f"Refreshing credentials with role arn : {role_arn}")
    sts_client = boto3.client("sts")
    response = sts_client.assume_role(
        RoleArn=role_arn,
        RoleSessionName="loan-monitor-backend-ecs-task-session",
        DurationSeconds=ttl,
    ).get("Credentials")

    credentials = {
        "access_key": response.get("AccessKeyId"),
        "secret_key": response.get("SecretAccessKey"),
        "token": response.get("SessionToken"),
    }

    logger.info("Credentials refreshed")
    return credentials
```

One point to note here is that sts boto client was still valid after 12 hours but boto session was not. With help of assume role 
function of sts client we are able to fetch new and valid(for specified TTL) credentials every time we would like to publish 
message to pubsub.

**self role problem**
```shell
{     "exc_info": true,
"event": "Error in refreshing credentials: An error occurred (AccessDenied) when calling the
AssumeRole operation: User: arn:aws:sts::756570553573:assumed-role/loan-monitor-backend-ecs-task/8c803531804044adb64e00c69379d5da
is not authorized to perform: sts:AssumeRole on resource: arn:aws:iam::756570553573:role/loan-monitor-backend-ecs-task",
 "timestamp": "2022-09-08T14:40:24.016698Z" }
```

**expose ARN of IAM role as output variable**
**Change in Tf module** (Looks like this change didn't help much)
```terraform
output "iam_role" {
  value = aws_iam_role.task.name
}

output "ecs_iam_role" {
  value = aws_iam_role.ecs.name
}
```

**Change in ecs.tf**
```terraform
{
    name  = "IAM_ROLE_ARN",
    value = module.aws_ecs_service_backend.iam_role_arn
},
```

## Example 

Link : https://itnext.io/terraform-tutorial-part-2-providers-and-resources-30121e28359b


```shell
resource "RESOURCE_NAME" "OBJECT_NAME" {
  argument1 = value1
  argument2 = value2
  nested_block1 {
    argument1 = value1
    argument2 = value2
  }
  nested_block2 {
    argument1 = value1
    argument2 = value2
  }

resource "tls_private_key" "my_private_key" {
  algorithm = "ED25519"
}

terraform init
terraform apply
```

----------------------

Rate Limiting : https://www.w3resource.com/python-exercises/decorator/python-decorator-exercise-7.php

**Dynamic Rate Limit** 
start with 5/minute later make in 10/minute
https://github.com/laurentS/slowapi/commit/4667152c902c61893b120dd1ad71ebedea5f4426

-------------------------

Teradata tutorial

https://www.tutorialspoint.com/teradata/teradata_architecture.htm

------------------